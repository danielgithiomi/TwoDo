import { Button } from "@tdw/atoms";
import { RoutePaths } from "../routes";
import { useNavigate } from "react-router-dom";
import alt_logo from "@tdw/images/branding/alt_logo.png";

export const Home = () => {
  const navigate = useNavigate();

  const handleNavigateToCreateNewUser = () => {
    navigate(RoutePaths.SignUp);
  };

  const handleNavigateToLogin = () => {
    navigate(RoutePaths.Login);
  };

  return (
    <>
      <img className="mx-auto" src={alt_logo} alt="Application Logo" />
      <div className="w-1/2 mx-auto flex gap-4">
        <Button
          id="to-create-new-user"
          label="Create New User"
          onClick={handleNavigateToCreateNewUser}
          className="border-white border p-2 rounded-md m-4 flex-1"
        />
        <Button
          id="to-login"
          label="Login"
          onClick={handleNavigateToLogin}
          className="border-white border p-2 rounded-md m-4 flex-1"
        />
      </div>
    </>
  );
};
