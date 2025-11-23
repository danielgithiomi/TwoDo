import { Button } from "@tdw/atoms";
import { DummyData } from "./../DummyData";
import alt_logo from "@tdw/images/branding/alt_logo.png";

export const Home = () => {
  return (
    <>
      <img src={alt_logo} alt="Application Logo" />
      <Button />
      <DummyData />
    </>
  );
};
