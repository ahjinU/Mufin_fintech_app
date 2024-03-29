import { Button } from '@/components';

export default function Complete() {
  return (
    <div className="absolute top-0 left-0 size-full bg-custom-white p-[4rem]">
      <p className="custom-bold-text">회원가입 완료!</p>
      <div className="fixed bottom-0 inset-x-0 p-[1.2rem]">
        <Button mode={'ACTIVE'} label={'계좌 만들기'}></Button>
      </div>
    </div>
  );
}
