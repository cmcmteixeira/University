namespace Client
{
    partial class DeleteAllForm
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
            this.AcceptButton = new System.Windows.Forms.Button();
            this.RefuseButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.NewPriceLabel = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // AcceptButton
            // 
            this.AcceptButton.Location = new System.Drawing.Point(77, 111);
            this.AcceptButton.Name = "AcceptButton";
            this.AcceptButton.Size = new System.Drawing.Size(75, 23);
            this.AcceptButton.TabIndex = 0;
            this.AcceptButton.Text = "Aceitar";
            this.AcceptButton.UseVisualStyleBackColor = true;
            // 
            // RefuseButton
            // 
            this.RefuseButton.Location = new System.Drawing.Point(77, 140);
            this.RefuseButton.Name = "RefuseButton";
            this.RefuseButton.Size = new System.Drawing.Size(75, 23);
            this.RefuseButton.TabIndex = 1;
            this.RefuseButton.Text = "Recusar";
            this.RefuseButton.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(63, 67);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(67, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Novo Preço:";
            // 
            // NewPriceLabel
            // 
            this.NewPriceLabel.AutoSize = true;
            this.NewPriceLabel.Location = new System.Drawing.Point(133, 67);
            this.NewPriceLabel.Name = "NewPriceLabel";
            this.NewPriceLabel.Size = new System.Drawing.Size(19, 13);
            this.NewPriceLabel.TabIndex = 3;
            this.NewPriceLabel.Text = "$$";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(63, 25);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(98, 13);
            this.label3.TabIndex = 4;
            this.label3.Text = "Alteração de Preço";
            // 
            // DeleteAllForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(234, 186);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.NewPriceLabel);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.RefuseButton);
            this.Controls.Add(this.AcceptButton);
            this.Name = "DeleteAllForm";
            this.Text = "DeleteAll";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        public System.Windows.Forms.Button AcceptButton;
        public System.Windows.Forms.Button RefuseButton;
        private System.Windows.Forms.Label label1;
        public System.Windows.Forms.Label NewPriceLabel;
        private System.Windows.Forms.Label label3;
    }
}