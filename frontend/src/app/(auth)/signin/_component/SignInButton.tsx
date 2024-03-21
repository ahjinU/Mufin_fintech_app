'use client';

import { Button } from '@/components';

export default function LoginButton() {
  const onSubmit = async () => {};

  return (
    <div className="my-[1rem]">
      <Button label="로그인" mode="ACTIVE" onClick={onSubmit} />
    </div>
  );
}
