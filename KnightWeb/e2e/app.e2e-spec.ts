import { KnightWebPage } from './app.po';

describe('knight-web App', () => {
  let page: KnightWebPage;

  beforeEach(() => {
    page = new KnightWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
