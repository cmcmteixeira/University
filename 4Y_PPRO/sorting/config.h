//
// Created by ventureoak on 18-06-2015.
//

#ifndef SORTING_CONFIG_H
#define SORTING_CONFIG_H

struct Configuration
{
    char* database;
    char*collection_user;
    char*collection_news;
    char*collection_news_digest;
    char* connection;
};

void inline daily_news_initialize_configurations(struct Configuration * config) {
    config->database = "ppro";
    config->collection_user = "user";
    config->collection_news = "news_entry";
    config->collection_news_digest = "news_digest";
    config->connection = "mongodb://localhost:27017/";
}

#endif //SORTING_CONFIG_H
