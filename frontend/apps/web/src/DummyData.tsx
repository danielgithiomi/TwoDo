import { getActiveUsers } from "@tdp/api";
import { useQuery } from "@tanstack/react-query";

export const DummyData = () => {
  const { data, isError, error } = useQuery({
    queryKey: ["users"],
    queryFn: () => getActiveUsers(),
  });

  if (isError) {
    return <div>Error: {error.message}</div>;
  }

  if (!data) {
    return <div>Loading...</div>;
  }

  return <div>Data: {JSON.stringify(data)}</div>;
};
