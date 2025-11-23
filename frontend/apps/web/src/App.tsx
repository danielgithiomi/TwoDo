import "./App.css";
import { Button } from "@tdw/atoms";
import { useGender } from "@tdp/api";
import { DummyData } from "./DummyData";
import alt_logo from "@tdw/images/branding/alt_logo.png";

function App() {
  const { genders } = useGender();

  return (
    <>
      <img src={alt_logo} alt="Application Logo" />
      <Button />
      <DummyData />

      <ul>
        {genders.map((gender) => (
          <li className="text-white" key={gender}>
            {gender}
          </li>
        ))}
      </ul>
    </>
  );
}

export default App;
