import React from "react";
import ContentBlock from "../components/ContentBlock";
import Button from "../components/Button";
import "../styles/PageCode.css"; 

const PageCode: React.FC = () => {
  return (
    <div className="page-container">
      <ContentBlock>
        <div className="page-content">
          <h4 className="page-title">Entrez votre code d'accès</h4>

          <div className="page-input-container">
            <input
              type="text"
              className="form-control w-50"
              placeholder="Code d'accès"
            />
          </div>

          <div className="page-button-container">
            <Button label="Valider" />
          </div>
        </div>
      </ContentBlock>
    </div>
  );
};

export default PageCode;
