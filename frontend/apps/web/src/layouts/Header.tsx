import type { FC } from "react";
import { Button } from "@tdw/atoms";
import { RoutePaths } from "@routes";
import { isAuthenticated, logout } from "@tdp/libs";
import alt_logo from "@tdw/images/branding/alt_logo.png";
import { useLocation, useNavigate } from "react-router-dom";


export const Header: FC = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const buttonText: string = isAuthenticated() ? "Logout" : "Login";
  const showButton: boolean = location.pathname !== RoutePaths.Login;

  function handleButtonClick() {
    if (isAuthenticated()) logout();
    navigate(isAuthenticated() ? RoutePaths.Home : RoutePaths.Login);
  }

  return (
    <header className="header">
      <div className="flex items-center">
        <img src={alt_logo} style={{ width: "80px" }} alt="application logo" />
        <p className="text-xl font-bold">TwoDo</p>
      </div>

      <div>
        <nav></nav>

        {showButton && (
          <Button
            id="btn-header-login"
            label={buttonText}
            variant="secondary"
            onClick={handleButtonClick}
          />
        )}
      </div>
    </header>
  );
};
