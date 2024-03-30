import { getServerSession } from 'next-auth';
import { authOptions } from '@/pages/api/auth/[...nextauth]';

type dataType = {
  data?: any;
  api: string;
};

export async function serverPostFetch(data: dataType) {
  const session = await getServerSession(authOptions);

  const res = await fetch(
    `${process.env.NEXT_PUBLIC_BASE_URL}/api${data.api}`,
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `${session?.Authorization}`,
      },
      body: JSON.stringify(data.data),
    },
  );
  return res.json();
}

export async function serverGetFetch(data: dataType) {
  const session = await getServerSession(authOptions);

  const res = await fetch(
    `${process.env.NEXT_PUBLIC_BASE_URL}/api${data.api}`,
    {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `${session?.Authorization}`,
      },
    },
  );
  return res.json();
}
