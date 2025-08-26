import React from "react";

interface DropDownProps {
    title: string;
}

const DropDown: React.FC<DropDownProps> = ({ title }) => {
    return (
        <div className="w-100">
            <h5 className="text-start mb-2 m-3">{title}</h5>
            <div className="dropdown px-2">
                <button
                    className="btn btn-block dropdown-toggle btn-outline-dark w-100 text-end fs-4"
                    type="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                >

                </button>
                <ul className="dropdown-menu ">
                    <li>
                        <button className="dropdown-item " type="button">
                            Action
                        </button>
                    </li>
                </ul>
            </div>
        </div>
    );
};

export default DropDown;