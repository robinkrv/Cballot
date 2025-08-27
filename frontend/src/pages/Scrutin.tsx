import React from "react";
import ContentBlock from "../components/ContentBlock";
import TextField from "../components/TextField";
import Button from "../components/Button";
import GestionBinomes from "../components/Binome";

// FFF9DB

const Scrutin = () => {
    return (
        <ContentBlock>
            <h1>Planification du scrutin</h1>
            <TextField title={"Date et heure du vote"} type={"datetime-local"} id={"VoteDate"} />
            <GestionBinomes/>
          
          <Button label="Valider"/>
        </ContentBlock>
    );
};

export default Scrutin;