import React from "react";
import ContentBlock from "../components/ContentBlock";
import Button from "../components/Button";
import "../styles/PageConnexion.css";

const PageConnexion: React.FC = () => {
  return (
    <div className="page-connexion">
      <ContentBlock>
        <h2 className="connexion-title">Connexion</h2>
        <form className="connexion-form">
          <div className="form-group">
            <label htmlFor="username" className="form-label">Identifiant</label>
            <input
              type="text"
              id="username"
              className="form-control"
              placeholder="Entrez votre identifiant"
            />
          </div>
          <div className="form-group">
            <label htmlFor="password" className="form-label">Mot de passe</label>
            <input
              type="password"
              id="password"
              className="form-control"
              placeholder="Entrez votre mot de passe"
            />
          </div>
          <div className="button-container">
            <Button label="Se connecter" />
          </div>
        </form>
      </ContentBlock>
    </div>
  );
};

export default PageConnexion;
