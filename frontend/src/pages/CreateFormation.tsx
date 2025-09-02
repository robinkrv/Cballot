import React from "react";
import ContentBlock from "../components/ContentBlock";
import DropDown from "../components/DropDown";
import TextField from "../components/TextField";
import Button from "../components/Button";
import ListName from "../components/ListName";
// import "../styles/CreateFormation.css"


// FFF9DB

const CreateFormation = () => {

    return (
        <ContentBlock>
            <h1>Creer une session</h1>
            <DropDown title={"Formation :"} />
            <TextField title={"Nom de la session :"} type={"text"} id={"sessionName"} />
            <TextField title={"Date de debut :"} type={"date"} id={"startDate"} />
            <TextField title={"Date de fin :"} type={"date"} id={"endDate"} />
            <h4>Liste d'éleves</h4>
            <ListName/>
            <Button label={"valider"} />
        </ContentBlock>
    );
};

export default CreateFormation;