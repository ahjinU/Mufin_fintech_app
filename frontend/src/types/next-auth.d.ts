import NextAuth from 'next-auth/next';
declare module 'next-auth' {
  interface Session {
    Authorization: string | null | undefined;
    refreshToken: string | null | undefined;
    // & DefaultSession['user'];
  }
  interface User {
    accessToken: string | null | undefined;
    refreshToken: string | null | undefined;
  }
}

declare module 'next-auth/jwt' {
  interface JWT {
    Authorization: string | null | undefined;
    refreshToken: string | null | undefined;
  }
}
