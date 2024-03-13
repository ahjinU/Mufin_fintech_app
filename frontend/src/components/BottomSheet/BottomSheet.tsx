import Image from 'next/image';
import Button from '../Button/Button';
import { XMarkIcon } from '@heroicons/react/24/solid';

interface BottomSheetProps {
  size: 'SMALL' | 'MEDIUM';
  title: string;
  content?: string;
  imageSrc: string;
  isXButtonVisible: boolean;
  onClick?: () => void;
  isOpen: boolean;
}

export default function BottomSheet({
  size,
  title,
  content,
  imageSrc,
  isXButtonVisible,
  isOpen,
  ...props
}: BottomSheetProps) {
  const height: string = size === 'SMALL' ? 'h-[30rem]' : 'h-[40rem]';
  const isButtonVisible: boolean = size !== 'SMALL';

  return (
    <section
      className={`absolute left-[0] top-[100dvh] transition-[height] duration-500 ease-in-out w-full min-w-[36rem] ${height} rounded-t-[1.6rem] bg-custom-white text-custom-black p-[3rem] flex flex-col justify-between`}
      {...props}
    >
      {isXButtonVisible && (
        <XMarkIcon className="w-[2.4rem] h-[2.4rem] absolute top-[2rem] right-[2rem] cursor-pointer" />
      )}

      <h1 className="custom-bold-text">{title}</h1>

      {content && <p className="custom-semibold-text text-wrap">{content}</p>}

      <Image
        width={100}
        height={100}
        src={imageSrc}
        alt={'bottom sheet image'}
        className="m-[2rem] self-center"
      ></Image>

      {isButtonVisible && <Button mode="ACTIVE" label="확인" />}
    </section>
  );
}
