import type { FC } from "react";
import { Button } from "@tdw/atoms";
import { RoutePaths } from "@routes";
import alt_logo from "@tdw/images/branding/alt_logo.png";
import { useLocation, useNavigate } from "react-router-dom";
import {isAuthenticated,RetrieveLoggedInUser,ClearStoredAuthentication} from "@tdp/libs";

export const Header: FC = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const buttonText: string = isAuthenticated() ? "Logout" : "Login";
  const showButton: boolean = location.pathname !== RoutePaths.Login;

  function handleButtonClick() {
    if (isAuthenticated()) ClearStoredAuthentication();
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

        <div className="flex items-center gap-3">
          {isAuthenticated() && (
            <img
              src={RetrieveLoggedInUser().avatarUrl}
              className="rounded-full size-10"
              alt="user profile picture"
            />
          )}

          {showButton && (
            <Button
              id="btn-header-login"
              label={buttonText}
              variant="secondary"
              onClick={handleButtonClick}
            />
          )}
        </div>
      </div>
    </header>
  );
};
