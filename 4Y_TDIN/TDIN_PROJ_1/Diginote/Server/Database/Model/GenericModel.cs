using System;
using MongoDB.Bson;

namespace Server.Database.Model
{
    [Serializable]
    class GenericModel
    {
        public ObjectId Id { get; set; }
        [NonSerialized]
        public String DatabaseName;

        public DateTime CreatedAt;

        public GenericModel()
        {
            DatabaseName = Database.GetDatabase().Name;
            CreatedAt = DateTime.Now;
        }
    }
}
