import React from "react";
import ContentBlock from "../components/ContentBlock";
import Button from "../components/Button";
import DropDown from "../components/DropDown";
import "../styles/PageVote.css";

const PageVote: React.FC = () => {
  return (
    <div className="page-vote page-container">
      <ContentBlock>
        <div className="page-content">
       
          <h4 className="page-title">
            Choisissez le binôme pour lequel vous souhaitez voter
          </h4>

          <div className="page-center">
            <div className="page-field w-50">
              <DropDown title="Sélectionner un binôme" items={[]} />
            </div>
          </div>

          <div className="page-button-container">
            <Button label="Voter" />
          </div>
        </div>
      </ContentBlock>
    </div>
  );
};

export default PageVote;
