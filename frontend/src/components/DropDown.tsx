import React from "react";

interface DropDownProps {
    title: string;
    items: string[];
    value?: string;
    onSelect?: (value: string) => void;
}

const DropDown: React.FC<DropDownProps> = ({ title , items,value, onSelect }) => {
    return (
        <div className="w-100">
      <h5 className="text-start mb-2 m-3">{title}</h5>
      <div className="dropdown px-2">
        <button
          className="btn btn-block dropdown-toggle btn-outline-dark w-100 text-start fs-4"
          type="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          {value || "Sélectionner..."}
        </button>
        <ul className="dropdown-menu">
          {items.map((item, index) => (
            <li key={index}>
              <button
                type="button"
                className="dropdown-item"
                onClick={() => onSelect && onSelect(item)}
              >
                {item}
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
    );
};

export default DropDown;