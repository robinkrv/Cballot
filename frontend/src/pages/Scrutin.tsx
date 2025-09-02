import React from "react";
import ContentBlock from "../components/ContentBlock";
import Button from "../components/Button";
import GestionBinomes from "../components/Binome";




const Scrutin = () => {
 
    return (
        <ContentBlock>
            <h1>Planification du scrutin</h1>


            <GestionBinomes />

         
            <Button label="Valider" />
        </ContentBlock>
    );
};

export default Scrutin;
