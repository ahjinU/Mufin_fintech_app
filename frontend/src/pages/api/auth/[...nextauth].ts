import NextAuth, { NextAuthOptions } from 'next-auth';
import CredentialsProvider from 'next-auth/providers/credentials';

export const authOptions: NextAuthOptions = {
  providers: [
    CredentialsProvider({
      name: 'credentials', // signIn(credentials) 형태로 사용
      credentials: {
        email: { label: 'email', type: 'text' },
        password: { label: 'password', type: 'password' },
      },

      async authorize(credentials) {
        try {
          const res = await fetch(
            `${process.env.NEXT_PUBLIC_BASE_URL}/api/user/login`,
            {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
              },
              body: JSON.stringify({
                email: credentials?.email,
                password: credentials?.password,
              }),
            },
          );

          if (res.ok) {
            const user = await res.json();
            user.accessToken = res.headers.get('authorization');
            user.refreshToken = res.headers.get('set-cookie');
            console.log('로그인 성공', user);
            return user;
          } else {
            console.log('잘못된 입력');
            return null;
          }
        } catch (error) {
          console.log('로그인 실패', error);
        }
      },
    }),
  ],
  pages: {
    signIn: '/login',
  },
  callbacks: {
    async jwt({ user, token }) {
      if (user) {
        token.Authorization = user.accessToken;
        token.refreshToken = user.refreshToken;
      }
      return token;
    },
    async session({ token, session }) {
      session.Authorization = token.Authorization;
      session.refreshToken = token.refreshToken;
      return session;
    },
  },
};

export default NextAuth(authOptions);
