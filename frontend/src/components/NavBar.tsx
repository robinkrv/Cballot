import React from "react";

const NavBar: React.FC = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <a className="navbar-brand" href="#">Accueil</a>
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav ms-auto">
          <li className="nav-item active">
            <a className="nav-link" href="#">Gestion Stagiaire</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">Gestion Formation</a>
          </li>
          <li className="nav-item">
            <a className="nav-link disabled" href="#">Déconnexion</a>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default NavBar;
