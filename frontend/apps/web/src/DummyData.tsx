import { useQuery } from "@tanstack/react-query";
import { getHelloWorld } from "../../../packages/api";

export const DummyData = () => {
  const { data } = useQuery({
    queryKey: ["dummy"],
    queryFn: () => getHelloWorld(),
  });

  return <div>Data: {JSON.stringify(data)}</div>;
};
