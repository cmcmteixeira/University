namespace Client
{
    partial class ClientForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.UserName = new System.Windows.Forms.Label();
            this.CurrentQuote = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.UserMoneyAmount = new System.Windows.Forms.Label();
            this.UserDiginoteAmount = new System.Windows.Forms.Label();
            this.DiginoteAmount = new System.Windows.Forms.TextBox();
            this.SellButton = new System.Windows.Forms.Button();
            this.BuyButton = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.pastTransactions = new System.Windows.Forms.DataGridView();
            this.PastDate = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Type = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.DigiAmount = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Buyer = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Seller = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Money = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.DeleteTransaction = new System.Windows.Forms.Button();
            this.currentTransactions = new System.Windows.Forms.DataGridView();
            this.Date = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.CurrentTransactionType = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.CurrentTransactionDigiAmount = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pastTransactions)).BeginInit();
            this.groupBox3.SuspendLayout();
            this.groupBox4.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.currentTransactions)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(30, 16);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(32, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "User:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 38);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(50, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Cotação:";
            // 
            // UserName
            // 
            this.UserName.AutoSize = true;
            this.UserName.Location = new System.Drawing.Point(68, 16);
            this.UserName.Name = "UserName";
            this.UserName.Size = new System.Drawing.Size(59, 13);
            this.UserName.TabIndex = 2;
            this.UserName.Text = "nome_user";
            // 
            // CurrentQuote
            // 
            this.CurrentQuote.AutoSize = true;
            this.CurrentQuote.Location = new System.Drawing.Point(68, 38);
            this.CurrentQuote.Name = "CurrentQuote";
            this.CurrentQuote.Size = new System.Drawing.Size(22, 13);
            this.CurrentQuote.TabIndex = 3;
            this.CurrentQuote.Text = "1.0";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(40, 61);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(22, 13);
            this.label5.TabIndex = 4;
            this.label5.Text = "$$:";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(11, 84);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(54, 13);
            this.label6.TabIndex = 5;
            this.label6.Text = "Diginotes:";
            // 
            // UserMoneyAmount
            // 
            this.UserMoneyAmount.AutoSize = true;
            this.UserMoneyAmount.Location = new System.Drawing.Point(69, 61);
            this.UserMoneyAmount.Name = "UserMoneyAmount";
            this.UserMoneyAmount.Size = new System.Drawing.Size(69, 13);
            this.UserMoneyAmount.TabIndex = 6;
            this.UserMoneyAmount.Text = "money_some";
            // 
            // UserDiginoteAmount
            // 
            this.UserDiginoteAmount.AutoSize = true;
            this.UserDiginoteAmount.Location = new System.Drawing.Point(68, 84);
            this.UserDiginoteAmount.Name = "UserDiginoteAmount";
            this.UserDiginoteAmount.Size = new System.Drawing.Size(98, 13);
            this.UserDiginoteAmount.TabIndex = 7;
            this.UserDiginoteAmount.Text = "diginotes_ammount";
            // 
            // DiginoteAmount
            // 
            this.DiginoteAmount.Location = new System.Drawing.Point(6, 19);
            this.DiginoteAmount.Name = "DiginoteAmount";
            this.DiginoteAmount.Size = new System.Drawing.Size(92, 20);
            this.DiginoteAmount.TabIndex = 10;
            // 
            // SellButton
            // 
            this.SellButton.Location = new System.Drawing.Point(104, 49);
            this.SellButton.Name = "SellButton";
            this.SellButton.Size = new System.Drawing.Size(89, 25);
            this.SellButton.TabIndex = 9;
            this.SellButton.Text = "Sell";
            this.SellButton.UseVisualStyleBackColor = true;
            // 
            // BuyButton
            // 
            this.BuyButton.Location = new System.Drawing.Point(104, 19);
            this.BuyButton.Name = "BuyButton";
            this.BuyButton.Size = new System.Drawing.Size(90, 24);
            this.BuyButton.TabIndex = 8;
            this.BuyButton.Text = "Buy";
            this.BuyButton.UseVisualStyleBackColor = true;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.BuyButton);
            this.groupBox1.Controls.Add(this.SellButton);
            this.groupBox1.Controls.Add(this.DiginoteAmount);
            this.groupBox1.Location = new System.Drawing.Point(667, 118);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(200, 90);
            this.groupBox1.TabIndex = 12;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Diginote";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.UserName);
            this.groupBox2.Controls.Add(this.label1);
            this.groupBox2.Controls.Add(this.label2);
            this.groupBox2.Controls.Add(this.UserDiginoteAmount);
            this.groupBox2.Controls.Add(this.CurrentQuote);
            this.groupBox2.Controls.Add(this.UserMoneyAmount);
            this.groupBox2.Controls.Add(this.label5);
            this.groupBox2.Controls.Add(this.label6);
            this.groupBox2.Location = new System.Drawing.Point(667, 12);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(200, 100);
            this.groupBox2.TabIndex = 13;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Info";
            // 
            // pastTransactions
            // 
            this.pastTransactions.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.pastTransactions.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.PastDate,
            this.Type,
            this.DigiAmount,
            this.Buyer,
            this.Seller,
            this.Money});
            this.pastTransactions.Location = new System.Drawing.Point(6, 19);
            this.pastTransactions.Name = "pastTransactions";
            this.pastTransactions.Size = new System.Drawing.Size(637, 169);
            this.pastTransactions.TabIndex = 14;
            // 
            // PastDate
            // 
            this.PastDate.HeaderText = "Date";
            this.PastDate.Name = "PastDate";
            // 
            // Type
            // 
            this.Type.HeaderText = "Type";
            this.Type.Name = "Type";
            // 
            // DigiAmount
            // 
            this.DigiAmount.HeaderText = "Digi.Amount";
            this.DigiAmount.Name = "DigiAmount";
            // 
            // Buyer
            // 
            this.Buyer.HeaderText = "Buyer";
            this.Buyer.Name = "Buyer";
            // 
            // Seller
            // 
            this.Seller.HeaderText = "Seller";
            this.Seller.Name = "Seller";
            // 
            // Money
            // 
            this.Money.HeaderText = "Money $$";
            this.Money.Name = "Money";
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.pastTransactions);
            this.groupBox3.Location = new System.Drawing.Point(12, 214);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(649, 196);
            this.groupBox3.TabIndex = 15;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Past Transactions";
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.DeleteTransaction);
            this.groupBox4.Controls.Add(this.currentTransactions);
            this.groupBox4.Location = new System.Drawing.Point(12, 12);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(649, 196);
            this.groupBox4.TabIndex = 16;
            this.groupBox4.TabStop = false;
            this.groupBox4.Text = "Transactions";
            this.groupBox4.Enter += new System.EventHandler(this.groupBox4_Enter);
            // 
            // DeleteTransaction
            // 
            this.DeleteTransaction.Location = new System.Drawing.Point(512, 20);
            this.DeleteTransaction.Name = "DeleteTransaction";
            this.DeleteTransaction.Size = new System.Drawing.Size(131, 24);
            this.DeleteTransaction.TabIndex = 1;
            this.DeleteTransaction.Text = "Eliminar";
            this.DeleteTransaction.UseVisualStyleBackColor = true;
            // 
            // currentTransactions
            // 
            this.currentTransactions.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.currentTransactions.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.Date,
            this.CurrentTransactionType,
            this.CurrentTransactionDigiAmount});
            this.currentTransactions.Location = new System.Drawing.Point(7, 20);
            this.currentTransactions.Name = "currentTransactions";
            this.currentTransactions.Size = new System.Drawing.Size(499, 150);
            this.currentTransactions.TabIndex = 0;
            // 
            // Date
            // 
            this.Date.HeaderText = "Date";
            this.Date.Name = "Date";
            // 
            // CurrentTransactionType
            // 
            this.CurrentTransactionType.HeaderText = "Type";
            this.CurrentTransactionType.Name = "CurrentTransactionType";
            // 
            // CurrentTransactionDigiAmount
            // 
            this.CurrentTransactionDigiAmount.HeaderText = "DigiAmount";
            this.CurrentTransactionDigiAmount.Name = "CurrentTransactionDigiAmount";
            // 
            // ClientForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1029, 416);
            this.Controls.Add(this.groupBox4);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Name = "ClientForm";
            this.Text = "Dashboard";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pastTransactions)).EndInit();
            this.groupBox3.ResumeLayout(false);
            this.groupBox4.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.currentTransactions)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        public System.Windows.Forms.Label UserName;
        public System.Windows.Forms.Label CurrentQuote;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        public System.Windows.Forms.Label UserMoneyAmount;
        public System.Windows.Forms.Label UserDiginoteAmount;
        public System.Windows.Forms.TextBox DiginoteAmount;
        public System.Windows.Forms.Button SellButton;
        public System.Windows.Forms.Button BuyButton;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        public System.Windows.Forms.DataGridView pastTransactions;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.GroupBox groupBox4;
        public System.Windows.Forms.Button DeleteTransaction;
        public System.Windows.Forms.DataGridView currentTransactions;
        private System.Windows.Forms.DataGridViewTextBoxColumn Date;
        public System.Windows.Forms.DataGridViewTextBoxColumn CurrentTransactionType;
        public System.Windows.Forms.DataGridViewTextBoxColumn CurrentTransactionDigiAmount;
        private System.Windows.Forms.DataGridViewTextBoxColumn PastDate;
        private System.Windows.Forms.DataGridViewTextBoxColumn Type;
        private System.Windows.Forms.DataGridViewTextBoxColumn DigiAmount;
        private System.Windows.Forms.DataGridViewTextBoxColumn Buyer;
        private System.Windows.Forms.DataGridViewTextBoxColumn Seller;
        private System.Windows.Forms.DataGridViewTextBoxColumn Money;
    }
}