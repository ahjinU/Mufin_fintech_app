import { signOut } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import { Button, NavText } from '@/components';

export default function SignOut() {
  const router = useRouter();

  return (
    <div className="flex flex-col gap-[1rem]">
      <Button
        mode={'ACTIVE'}
        label={'로그아웃'}
        onClick={() => signOut()}
      ></Button>
      <div className="flex justify-end">
        <NavText link="/" text="홈으로 돌아가기" />
      </div>
    </div>
  );
}
