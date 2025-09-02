import React, { useState } from "react";
import ContentBlock from "../components/ContentBlock";
import Button from "../components/Button";
import { login } from "../api/AdminLoginApi";
import "../styles/PageConnexion.css";

const PageConnexion: React.FC = () => {

  const [mail, setMail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);
  const [admin, setAdmin] = useState<any>(null);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null)
  

  try {
     const result = await login({ mail, password });
      setAdmin(result); 
      console.log("Connecté :", result);
  }
  catch (err: any) {
      setError(err.message);
  }
};
  return (
    <div className="page-connexion">
      <ContentBlock>
        <h2 className="connexion-title">Connexion</h2>
        <form className="connexion-form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="username" className="form-label">Identifiant</label>
            <input
              type="text"
              id="username"
              className="form-control"
              placeholder="Entrez votre identifiant"
              value={mail}
              onChange={(e) => setMail(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="password" className="form-label">Mot de passe</label>
            <input
              type="password"
              id="password"
              className="form-control"
              placeholder="Entrez votre mot de passe"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <div className="button-container">
            <Button label="Se connecter" />
          </div>
        </form>
        {error && <p className="error-message">{error}</p>}
        {admin && <p className="success-message">Bienvenue {admin.name} !</p>}
      </ContentBlock>
    </div>
  );
};

export default PageConnexion;
