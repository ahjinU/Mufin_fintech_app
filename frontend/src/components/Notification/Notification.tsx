import { XMarkIcon } from '@heroicons/react/16/solid';
import Image from 'next/image';

interface NotificationProps {
  index: number;
  message: string;
  type: string;
  submessage?: string;
}

export default function Notification({
  index,
  message,
  type,
  submessage,
}: NotificationProps) {
  let icon: string;
  switch (type) {
    case '대출거절':
      icon = 'http://localhost:3000/images/alert/icon-loan-reject.png';
      break;
    case '대출승인':
      icon = 'http://localhost:3000/images/alert/icon-loan-approve.png';
      break;
    case '용돈':
      icon = 'http://localhost:3000/images/alert/icon-pin-money.png';
      break;
    case '더치페이':
    case '적금':
    case '적금 만기':
      icon = 'http://localhost:3000/images/alert/icon-money.png';
      break;
    default:
      icon = 'http://localhost:3000/images/alert/icon-megaphone.png';
  }

  return (
    <div
      className={`w-full h-auto ${
        index % 2 === 0 ? 'bg-custom-light-gray' : 'bg-custom-white'
      } flex gap-[0.5rem] flex-col py-[1rem]`}
    >
      <div className="w-full flex flex-row h-[3rem] items-center">
        <div className="w-[3rem]">
          <Image src={icon} width={100} height={100} alt="없음" />
        </div>
        <p className="custom-semibold-text text-custom-black">{type}</p>
        <XMarkIcon className="w-[2.4rem] h-[2.4rem] absolute right-[2rem] cursor-pointer" />
      </div>
      <div className="flex flex-col gap-[1rem] px-[1.5rem]">
        <p className="custom-medium-text text-custom-black">{message}</p>
        <p className="custom-medium-text text-custom-black">{submessage}</p>
      </div>
    </div>
  );
}
