#include <mongoc.h>
#include <bson-types.h>
#include "config.h"
#include "log.h"

void printQuery(bson_t* query){
    size_t len;
    char *str;
    str = bson_as_json (query, &len);
    printf ("\n\n%s\n\n", str);
    bson_free (str);
}


int main (int   argc, char *argv[])
{
    debug_print("Starting");
    mongoc_init ();


    struct Configuration dn_config;
    daily_news_initialize_configurations(&dn_config);

    debug_print("DB:CONF:NAME:%s ",dn_config.database);
    debug_print("DB:CONFG:STR:%s ",dn_config.connection);

    mongoc_client_t *client;
    client          = mongoc_client_new (dn_config.connection);
    debug_print("DB:CONNECT:Connected to %s",dn_config.database);

    time_t now = NULL;
    time(&now);
    /*****************
     * Fetching Users
     *****************/
    bson_t*               query_users       = bson_new();;
    mongoc_collection_t*  user_collection   = mongoc_client_get_collection(client, dn_config.database, dn_config.collection_user);
    mongoc_collection_t*  news_collection   = mongoc_client_get_collection(client, dn_config.database, dn_config.collection_news);
    mongoc_collection_t*news_diggest_collection = mongoc_client_get_collection(client, dn_config.database, dn_config.collection_news_digest);
    mongoc_cursor_t*      user_cursor       = mongoc_collection_find (user_collection, MONGOC_QUERY_NONE, 0, 0, 0,query_users, NULL, NULL);
    const bson_t*         user;
    int                   user_count        = mongoc_collection_count(user_collection,MONGOC_QUERY_NONE,NULL,0,0,0,0)-1;

    while (mongoc_cursor_next(user_cursor, &user)) {
        bson_iter_t user_prop_it;

        // Printing UserName
        bson_iter_init(&user_prop_it,user);
        bson_iter_find(&user_prop_it,"username");
        const bson_value_t * user_name= bson_iter_value(&user_prop_it);
        debug_print("Sorting News For User: %-15s , %d to go",user_name->value.v_utf8.str,user_count--);

        // Fetching news sources
        bson_iter_t user_news_source_it;
        bson_iter_init(&user_prop_it,user);
        bson_iter_find(&user_prop_it,"newsSources");
        bson_iter_recurse(&user_prop_it,&user_news_source_it);
        /***********************************************************
         * CREATING QUERY >= YESTERDAY AND SOURCE FROM THE USER LIST
         ************************************************************/
        bson_t* oids = bson_new();
        uint32_t count = 0;
        while(bson_iter_next(&user_news_source_it)){
            char id[25];
            bson_oid_t id_ = bson_iter_value(&user_news_source_it)->value.v_oid;
            bson_oid_to_string(&id_,id);
            char str[16];
            const char *key;
            bson_uint32_to_string (count, &key, str, sizeof str);
            BSON_APPEND_OID(oids,key,&bson_iter_value(&user_news_source_it)->value.v_oid);
            count++;
        }
        bson_t*query_user_source_ = bson_new();
        BSON_APPEND_ARRAY(query_user_source_,"$in",oids);

        bson_t*query_user_source = bson_new();
        BSON_APPEND_DOCUMENT(query_user_source,"sourceId", query_user_source_);

        bson_t* query_bigger_yesterday = BCON_NEW("publishedAt","{","$gt",BCON_INT32(now - 7*24*60*60), "}");

        bson_t* queries = bson_new();
        BSON_APPEND_DOCUMENT(queries,"0",query_bigger_yesterday);
        BSON_APPEND_DOCUMENT(queries,"1", query_user_source);

        bson_t* query_and = bson_new();
        BSON_APPEND_ARRAY(query_and,"$and",queries);

        bson_t* query_order = BCON_NEW("relevance",BCON_INT32(-1));
        bson_t* query_limit = BCON_NEW("limit"    ,BCON_INT32(10));

        bson_t* query = bson_new();
        BSON_APPEND_DOCUMENT(query,"$query",query_and);
        BSON_APPEND_DOCUMENT(query,"$orderby",query_order);

        mongoc_cursor_t* news_cursor = mongoc_collection_find(news_collection,MONGOC_QUERY_NONE,0,10,0,query,NULL,NULL);

        bson_t*user_news_array = bson_new();
        const bson_t* news_articles;
        count = 0;
        while(mongoc_cursor_next(news_cursor,&news_articles)){
            char str[16];
            const char *key;
            bson_uint32_to_string (count, &key, str, sizeof str);
            BSON_APPEND_DOCUMENT(user_news_array, key, news_articles);
            count++;
        }
        bson_t* user_news = bson_new();

        BSON_APPEND_ARRAY(user_news,"dailyNews",user_news_array);
        /************************
         * INSERTING NEWs DOCUMENT
         ************************/
        bson_oid_t oid;
        bson_oid_init (&oid, NULL);

        bson_iter_init(&user_prop_it,user);
        bson_iter_find(&user_prop_it,"_id");

        const bson_value_t * user_id = bson_iter_value(&user_prop_it);

        BSON_APPEND_OID(user_news, "_id", &oid);
        BSON_APPEND_OID(user_news, "user", &user_id->value.v_oid);
        BSON_APPEND_INT64(user_news,"date",now);

        mongoc_collection_insert(news_diggest_collection,MONGOC_INSERT_NONE,user_news,NULL,NULL);
        mongoc_cursor_destroy(news_cursor);
    }
    mongoc_cursor_destroy (user_cursor);
    mongoc_collection_destroy (user_collection);
    mongoc_collection_destroy (news_collection);
    mongoc_collection_destroy (news_diggest_collection);
    mongoc_client_destroy (client);
    return 0;
}