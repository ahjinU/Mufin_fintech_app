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
        console.log('여기 들어옴?');

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
            console.log('로그인되었다!', user);
            console.log(res.headers)
            return user;
          } else {
            console.log('사용자가 잘못한듯');
            return null;
          }
        } catch (error) {
          console.log('아 몬가 잘못됨', error);
        }
      },
    }),
  ],
  pages: {
    signIn: '/login',
  },
  callbacks: {
    async jwt({ token }) {
      console.log('토킁', token);
      return token;
    },
    async session({ token, session }) {
      console.log('저장할 유저 정보?', session);
      // session.user.accessToken = token
      return session;
    },
  },
  // session: {
  //   jwt: true,
  // },
};

export default NextAuth(authOptions);
