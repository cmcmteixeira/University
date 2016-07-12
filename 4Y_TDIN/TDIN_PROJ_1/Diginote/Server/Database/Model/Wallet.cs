using System.Collections;
using System.Collections.Generic;
using MongoDB.Driver;

namespace Server.Database.Model
{
    class Wallet : GenericModel
    {
        public Diginote[] Diginotes;
        public double Money = 20;
        
        public void Initialize()
        {
            var diginotesTemp = new List<Diginote>();
            for (var i = 0; i < 20; i++)
            {
                diginotesTemp.Add(new Diginote());
            }
            this.Diginotes = diginotesTemp.ToArray();
        }
    }

}
