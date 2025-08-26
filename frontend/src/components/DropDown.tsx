import React from "react";

interface DropDownProps {
    title: string; // Le texte du bouton
}

const DropDown: React.FC<DropDownProps> = ({ title }) => {
    return (
        <div className="dropdown d-grid">
            <button
                className="btn btn-block dropdown-toggle btn-outline-dark "
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
            >
                {title}
            </button>
            <ul className="dropdown-menu ">
                <li>
                    <button className="dropdown-item " type="button">
                        Action
                    </button>
                </li>
            </ul>
        </div>
    );
};

export default DropDown;