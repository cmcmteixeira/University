namespace Client
{
    partial class InitialForm
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
            this.LoginButton = new System.Windows.Forms.Button();
            this.RegistryButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // LoginButton
            // 
            this.LoginButton.Location = new System.Drawing.Point(107, 103);
            this.LoginButton.Name = "LoginButton";
            this.LoginButton.Size = new System.Drawing.Size(75, 23);
            this.LoginButton.TabIndex = 0;
            this.LoginButton.Text = "Login";
            this.LoginButton.UseVisualStyleBackColor = true;
            // 
            // RegistryButton
            // 
            this.RegistryButton.Location = new System.Drawing.Point(107, 132);
            this.RegistryButton.Name = "RegistryButton";
            this.RegistryButton.Size = new System.Drawing.Size(75, 23);
            this.RegistryButton.TabIndex = 1;
            this.RegistryButton.Text = "Registo";
            this.RegistryButton.UseVisualStyleBackColor = true;
            // 
            // InitialForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 261);
            this.Controls.Add(this.RegistryButton);
            this.Controls.Add(this.LoginButton);
            this.Name = "InitialForm";
            this.Text = "InitialForm";
            this.ResumeLayout(false);

        }

        #endregion

        public System.Windows.Forms.Button LoginButton;
        public System.Windows.Forms.Button RegistryButton;
    }
}