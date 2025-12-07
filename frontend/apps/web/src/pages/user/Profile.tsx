import { RetrieveLoggedInUser } from "@tdp/libs";
import type { UserResponseDTO as User } from "@tdp/types";

export const UserProfile = () => {
  const loggedInUser: User = RetrieveLoggedInUser();
  const { firstName, lastName, email, username, roles } = loggedInUser;

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
            <span className="italic underline font-semibold underline-offset-2">
              {role} {index < roles.length - 1 ? ", " : ""}
            </span>
          ))}
          ]
        </li>
      </ul>
    </div>
  );
};
