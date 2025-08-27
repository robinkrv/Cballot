import React from "react";
import NavBar from "../components/NavBar";
import ContentBlock from "../components/ContentBlock";
import DropDown from "../components/DropDown";
import SearchBar from "../components/SearchBar";
import Button from "../components/Button";
import "../styles/PageSession.css";

const PageSession: React.FC = () => {
  return (
    <div className="page-session">
      <NavBar />
      <div className="d-flex justify-content-center align-items-center min-vh-100 bg-warning bg-opacity-25">
        <ContentBlock>
          {/* DropDown */}
          <div className="mb-4 text-start">
            <label className="form-label">Nom de la session</label>
            <DropDown title="Sélectionner une session" />
          </div>

          {/* SearchBar */}
          <SearchBar label="Candidat" placeholder="Rechercher un candidat..." />

          {/* Button */}
          <div className="text-center mt-4">
            <Button label="Initialiser session de vote" />
          </div>
        </ContentBlock>
      </div>
    </div>
  );
};

export default PageSession;
