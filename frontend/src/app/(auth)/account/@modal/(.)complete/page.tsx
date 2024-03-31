import { Button } from '@/components';
import Link from 'next/link';

export default function Complete() {
  return (
    <div className="absolute top-0 left-0 size-full bg-custom-white p-[4rem]">
      <p className="custom-bold-text">계좌 개설 완료!</p>
      <div className="fixed bottom-0 inset-x-0 p-[1.2rem]">
        <Link href="/" replace>
          <Button mode={'ACTIVE'} label={'홈으로 가기'}></Button>
        </Link>
      </div>
    </div>
  );
}
