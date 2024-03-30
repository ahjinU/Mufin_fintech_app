import { NavText, Header, NavBar } from '@/components';
import Lottie from 'react-lottie-player';
import lottieJson from '../../../public/lotties/success.json';

export default function Complete({
  title,
  content,
  navText,
  link,
}: {
  title: string;
  content: string;
  navText: string;
  link: string;
}) {
  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[2rem]">
        <Header>
          <h1 className="custom-bold-text">{title}</h1>
        </Header>
        <p className="custom-medium-text">{content}</p>
        <div className="mx-auto">
          <Lottie
            loop
            animationData={lottieJson}
            play
            style={{ width: 300, height: 300 }}
          />
        </div>
        <NavText text={navText} link={link} />
      </section>
      <NavBar mode="CHILD" />
    </>
  );
}
