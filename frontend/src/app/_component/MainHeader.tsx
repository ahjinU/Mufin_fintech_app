import Image from 'next/image';

export default function MainHeader() {
  return (
    <div className="h-[5.6rem] px-[2rem] flex justify-start items-center gap-[0.8rem]">
      <Image
        src={'/images/icon-app-logo.png'}
        width={50}
        height={50}
        alt={'mufin'}
        className="w-[3.2rem] h-[2.8rem]"
      ></Image>
      <Image
        src={'/images/icon-mufin.png'}
        width={316}
        height={96}
        alt={'mufin'}
        className="w-[7.2rem] h-[2.2rem] mb-[0.4rem]"
      ></Image>
    </div>
  );
}
