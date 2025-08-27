// import React from "react";

interface SearchBarProps {
  label: string;
  placeholder?: string;
}

const SearchBar: React.FC<SearchBarProps> = ({ label, placeholder }) => {
  return (
    <div className="mb-3">
      <label className="form-label d-block text-start">{label}</label>
      <input
        type="text"
        className="form-control"
        placeholder={placeholder ?? ""}
      />
    </div>
  );
};

export default SearchBar;

//     );
// }

