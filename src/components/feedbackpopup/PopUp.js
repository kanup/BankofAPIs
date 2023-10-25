import React from 'react';
import { FaTimes } from 'react-icons/fa';
const PopUp = ({ message, onClose }) => {
    return (

        <div className="popup">
            <div className="popup-content">
                <div style={{ textAlign: "right" }}>
                    <FaTimes onClick={onClose} />
                </div>
                <div>
                    <p>{message}</p>
                </div>
            </div>
        </div>

    );
}

export default PopUp;
