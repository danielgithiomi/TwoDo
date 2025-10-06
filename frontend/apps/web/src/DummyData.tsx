import { useQuery } from "@tanstack/react-query";
import { getHelloWorld } from "../../../packages/api";

export const DummyData = () => {
  const { data, isError, error } = useQuery({
    queryKey: ["dummy"],
    queryFn: () => getHelloWorld(),
  });

  if (isError) {
    return <div>Error: {error.message}</div>;
  }

  if (!data) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      Data: {data.message} - {data.timestamp}
    </div>
  );
};
