import { Button } from "@tdw/atoms";
import { RoutePaths } from "../routes";
import { DummyData } from "./../DummyData";
import { useNavigate } from "react-router-dom";
import alt_logo from "@tdw/images/branding/alt_logo.png";

export const Home = () => {
  const navigate = useNavigate();

  const handleNavigateToCreateNewUser = () => {
    navigate(RoutePaths.SignUp);
  };

  return (
    <>
      <img className="mx-auto" src={alt_logo} alt="Application Logo" />
      <Button
        id="to-create-new-user"
        label="Create New User"
        onClick={handleNavigateToCreateNewUser}
        className="border-white border p-2 rounded-md m-4"
      />
      <DummyData />
    </>
  );
};
