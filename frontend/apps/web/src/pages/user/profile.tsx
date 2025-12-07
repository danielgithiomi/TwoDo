import {logout} from "@tdp/libs";
import {Button} from "@tdw/atoms";
import {RoutePaths} from "@routes";
import {useNavigate} from "react-router-dom";

export const UserProfile = () => {
    const navigate = useNavigate();

    function handleLogout() {
        logout();
        navigate(RoutePaths.Home);
    }

    return (
        <div className="min-h-screen">
            <div className="w-full flex justify-end">
                <Button label="Logout" id="logout-btn" onClick={handleLogout}/>
            </div>

            <a>Profile</a>
        </div>
    );
};
