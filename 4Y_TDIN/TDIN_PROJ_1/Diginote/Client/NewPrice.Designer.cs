namespace Client
{
    partial class NewPriceForm
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
            this.NewPriceInput = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.SubmitButton = new System.Windows.Forms.Button();
            this.CurrentPrice = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(50, 95);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(64, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Novo Preço";
            // 
            // NewPriceInput
            // 
            this.NewPriceInput.Location = new System.Drawing.Point(120, 95);
            this.NewPriceInput.Name = "NewPriceInput";
            this.NewPriceInput.Size = new System.Drawing.Size(100, 20);
            this.NewPriceInput.TabIndex = 1;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(50, 59);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(62, 13);
            this.label2.TabIndex = 2;
            this.label2.Text = "Preço Atual";
            // 
            // SubmitButton
            // 
            this.SubmitButton.Location = new System.Drawing.Point(80, 166);
            this.SubmitButton.Name = "SubmitButton";
            this.SubmitButton.Size = new System.Drawing.Size(123, 45);
            this.SubmitButton.TabIndex = 3;
            this.SubmitButton.Text = "Submeter";
            this.SubmitButton.UseVisualStyleBackColor = true;
            // 
            // CurrentPrice
            // 
            this.CurrentPrice.AutoSize = true;
            this.CurrentPrice.Location = new System.Drawing.Point(117, 59);
            this.CurrentPrice.Name = "CurrentPrice";
            this.CurrentPrice.Size = new System.Drawing.Size(13, 13);
            this.CurrentPrice.TabIndex = 4;
            this.CurrentPrice.Text = "0";
            // 
            // NewPriceForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 261);
            this.Controls.Add(this.CurrentPrice);
            this.Controls.Add(this.SubmitButton);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.NewPriceInput);
            this.Controls.Add(this.label1);
            this.Name = "NewPriceForm";
            this.Text = "NewPrice";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        public System.Windows.Forms.TextBox NewPriceInput;
        private System.Windows.Forms.Label label2;
        public System.Windows.Forms.Button SubmitButton;
        public System.Windows.Forms.Label CurrentPrice;
    }
}