import { useEffect } from "react";
import { RoutePaths } from "@routes";
import { useNavigate } from "react-router-dom";
import type { UserResponseDTO as User } from "@tdp/types";
import { RetrieveLoggedInUser, isAuthenticated } from "@tdp/libs";

export const UserProfile = () => {
  const navigate = useNavigate();
  const loggedInUser: User = RetrieveLoggedInUser();
  const { firstName, lastName, email, username, roles } = loggedInUser;

  // Navigate to login if no user is found
  useEffect(() => {
    if (!isAuthenticated() || !loggedInUser) {
      navigate(RoutePaths.Home);
    }
  }, [navigate, isAuthenticated, loggedInUser]);

  return (
    <div>
      <h3 className="font-bold uppercase">User Profile</h3>

      <ul>
        <li>First Name: {firstName}</li>
        <li>Last Name: {lastName}</li>
        <li>Email: {email}</li>
        <li>Username: {username}</li>
        <li>
          Roles: [
          {roles.map((role, index) => (
            <span
              key={index}
              className="italic underline font-semibold underline-offset-2"
            >
              {role} {index < roles.length - 1 ? ", " : ""}
            </span>
          ))}
          ]
        </li>
      </ul>
    </div>
  );
};
