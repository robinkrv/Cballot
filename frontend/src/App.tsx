import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import PageCode from "./pages/PageCode";
import PageVote from "./pages/PageVote";
import PageConnexion from "./pages/PageConnexion";
import PageSession from "./pages/PageSession";
import Scrutin from "./pages/Scrutin";
import CreateFormation from "./pages/CreateFormation";

const App: React.FC = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<PageCode />} />
        <Route path="/vote" element={<PageVote />} />
        <Route path="/connexion" element={<PageConnexion />} />
        <Route path="/session" element={<PageSession />} />
        <Route path="/scrutin" element={<Scrutin/>}/>
        <Route path="/creationSession" element={<CreateFormation/>}/>
      </Routes>
    </BrowserRouter>
  );
};

export default App;
