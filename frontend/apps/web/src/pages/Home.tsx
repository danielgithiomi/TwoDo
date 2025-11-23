import { Button } from "@tdw/atoms";
import { DummyData } from "./../DummyData";
import alt_logo from "@tdw/images/branding/alt_logo.png";

export const Home = () => {
  return (
    <>
      <img src={alt_logo} alt="Application Logo" />
      <Button
        id="to-create-new-user"
        label="Create New User"
        className="border border-white p-2"
        onClick={() => alert("Button clicked")}
      />
      <DummyData />
    </>
  );
};
