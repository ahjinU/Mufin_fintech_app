import NextAuth from 'next-auth';
import CredentialsProvider from 'next-auth/providers/credentials';

export const authOptions = {
  providers: [
    CredentialsProvider({
      name: 'credentials',
      credentials: {
        email: { label: 'email', type: 'text' },
        password: { label: 'password', type: 'password' },
      },

      async authorize(credentials) {
        fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/api/v1/user/login`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            email: credentials?.email,
            password: credentials?.password,
          }),
        })
          .then((response) => {
            const user = response.json();
            const token = response.headers.get('Authorization');

            if (user) {
              return {
                token: token,
              };
            } else {
              return null;
            }
          })
          .catch((error) => {
            console.error(
              'There was a problem with the fetch operation:',
              error,
            );
          });
      },
    }),
  ],
  callbacks: {
    async session(session, token) {
      session.token = token.token;
      return session;
    },
  },
  session: {
    jwt: true,
  },
  pages: {
    signIn: '/signin',
  },
};
export default NextAuth(authOptions);
