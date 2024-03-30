import { signOut } from 'next-auth/react';
import { Button, NavText } from '@/components';

export default function SignOut() {
  const handleClick = () => {
    signOut();
    localStorage.removeItem('userStore');
  };

  return (
    <div className="flex flex-col gap-[1rem]">
      <Button mode={'ACTIVE'} label={'로그아웃'} onClick={handleClick}></Button>
      <div className="flex justify-end">
        <NavText link="/" text="홈으로 돌아가기" />
      </div>
    </div>
  );
}
