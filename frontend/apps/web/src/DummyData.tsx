import { GenderService, getActiveUsers } from "@tdw/api";
import { useQuery } from "@tanstack/react-query";

export const DummyData = () => {
  const { data, isError, error } = useQuery({
    queryKey: ["users"],
    queryFn: () => getActiveUsers(),
  });

  const { data: genderData } = useQuery<string[]>({
    queryKey: ["genders"],
    queryFn: () => GenderService.getApplicationGenders(),
  });

  console.log("Genders found:", genderData);

  if (isError) {
    return <div>Error: {error.message}</div>;
  }

  if (!data) {
    return <div>Loading...</div>;
  }

  return <div>Data: {JSON.stringify(data)}</div>;
};
