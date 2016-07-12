using MongoDB.Driver;

namespace Server.Database
{
    class Collection<T> where T :  Model.GenericModel
    {
        public static MongoCollection<T> GetCollection()
        {
            
            var db = Database.GetDatabase();
            return db.GetCollection<T>(Database.Collections[typeof(T)]);

        }

        public static T FollowReference(MongoDBRef reference)
        {
            var db = Database.GetDatabase();
            return db.FetchDBRefAs<T>(reference);
        }

        
    }
}
