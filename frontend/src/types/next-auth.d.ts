import { DefaultSession } from 'next-auth';
import NextAuth from 'next-auth/next';

declare module 'next-auth' {
  interface Session {
    user: {
      accessToken: string | null | undefined;
      // refreshToken: string | null | undefined;
    } & DefaultSession["user"];
  }
  interface User {
    accessToken: string | null | undefined;
    refreshToken: string | null | undefined;
  }
}
