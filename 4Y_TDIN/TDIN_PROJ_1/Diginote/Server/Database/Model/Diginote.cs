using System;
using System.Security.Cryptography;
using System.Text;

namespace Server.Database.Model
{
    class Diginote
    {
        public String Code;

        public Diginote()
        {
            var random = new Random(Guid.NewGuid().GetHashCode());
            var str = DateTime.Now.ToBinary() + random.NextDouble().ToString();

            var md5 = MD5.Create();
            var inputBytes = Encoding.ASCII.GetBytes(str);
            var hash = md5.ComputeHash(inputBytes);

            var sb = new StringBuilder();
            foreach (var t in hash)
            {
                sb.Append(t.ToString("X2"));
            }
            Code = sb.ToString();
        }
    }
}
