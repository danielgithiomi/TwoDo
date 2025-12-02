import { Button } from "@tdw/atoms";
import { RoutePaths } from "../routes";
import { useNavigate } from "react-router-dom";
import alt_logo from "@tdw/images/branding/alt_logo.png";

export const Home = () => {
  const navigate = useNavigate();

  const handleNavigateToRegister = () => {
    navigate(RoutePaths.Register);
  };

  const handleNavigateToLogin = () => {
    navigate(RoutePaths.Login);
  };

  return (
    <>
      <img className="mx-auto" src={alt_logo} alt="Application Logo" />
      <div className="w-full lg:w-1/2 mx-auto flex gap-4">
        <Button
          id="btn-to-register"
          label="Register"
          onClick={handleNavigateToRegister}
          className="p-2 rounded-md m-4 flex-1"
        />
        <Button
          id="btn-to-login"
          label="Login"
          onClick={handleNavigateToLogin}
          className="p-2 rounded-md m-4 flex-1"
        />
      </div>
    </>
  );
};
