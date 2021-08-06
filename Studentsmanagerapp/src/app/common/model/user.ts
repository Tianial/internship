export interface User {
  id?: number;
  username?: string;
  roles?: Role[];
}

interface Role {
  id?: number;
  name?: string;
}
